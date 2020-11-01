package com.example.vviped


import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Build
import android.os.Bundle
import android.os.Environment.getExternalStorageDirectory
import android.provider.MediaStore
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vviped.model.Chat
import kotlinx.android.synthetic.main.activity_chat.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MainChat : AppCompatActivity(), View.OnClickListener {
    private lateinit var listViewType: MutableList<Int>
    private lateinit var listChat: MutableList<Chat>
    private lateinit var adapterChat: AdapterChat

    private val requestCodeGallery = 1
    private val requestCodeCamera = 2
    private val requestCodePermission = 100


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        checkRuntimePermissions()
        input_message.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                sendTextMessage()
            }
            true
        }

        btn_BackFromChat.setOnClickListener {
            onBackPressed()
        }

        btnSendMessage.setOnClickListener {
            sendTextMessage()
        }
        btnSendImage.setOnClickListener {
            sendImageMessage()
        }
        default_msg_1.setOnClickListener(this)
        default_msg_2.setOnClickListener(this)
        default_msg_3.setOnClickListener(this)
        default_msg_4.setOnClickListener(this)

        setupAdapterRecyclerView()
    }

    private fun checkRuntimePermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA
                ), requestCodePermission
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        grantResults.forEach {
            if (it == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(
                    this,
                    "Permission for camera usage and photo is needed for full functional app",
                    Toast.LENGTH_SHORT
                )
                    .show()
                finish()
            }
        }
    }

    private fun sendImageMessage() {
        val idTypeChat = radiogroup_chat.checkedRadioButtonId
        val typeChat = if (idTypeChat == R.id.radioButton_myself) {
            AdapterChat.VIEW_TYPE_MY_SELF
        } else {
            AdapterChat.VIEW_TYPE_USER
        }

        val builderAlertDialog = AlertDialog.Builder(this)
            .setTitle("Choose Action")
            .setItems(arrayOf("Gallery", "Camera")) { dialogInterface, indexItem ->
                dialogInterface.dismiss()
                when (indexItem) {
                    0 -> {
                        val intentImagePicker = Intent(Intent.ACTION_PICK)
                        intentImagePicker.type = "image/"
                        startActivityForResult(intentImagePicker, requestCodeGallery)
                    }
                    1 -> {
                        val intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        startActivityForResult(intentCamera, requestCodeCamera)
                    }
                }
            }
        val alertDialog = builderAlertDialog.create()
        alertDialog.show()
    }

    private fun sendTextMessage() {
        val idTypeChat = radiogroup_chat.checkedRadioButtonId
        val typeChat = if (idTypeChat == R.id.radioButton_myself) {
            AdapterChat.VIEW_TYPE_MY_SELF
        } else {
            AdapterChat.VIEW_TYPE_USER
        }
        val message = input_message.text.toString().trim()
        if (message.isEmpty()) {
            Toast.makeText(this@MainChat, "Message is empty", Toast.LENGTH_SHORT).show()
        } else {
            val dateTime = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.US).format(Date())
            val chat = Chat(message = message, dateTime = dateTime, image = "")
            listViewType.add(typeChat)
            listChat.add(chat)
            adapterChat.notifyDataSetChanged()
            input_message.setText("")
            // close keyboard after done
            var im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            im.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }


    }

    private fun sendDefaultTextMessage(message: String) {
        val idTypeChat = radiogroup_chat.checkedRadioButtonId
        val typeChat = if (idTypeChat == R.id.radioButton_myself) {
            AdapterChat.VIEW_TYPE_MY_SELF
        } else {
            AdapterChat.VIEW_TYPE_USER
        }
        if (message.isEmpty()) {
            Toast.makeText(this@MainChat, "Message is empty", Toast.LENGTH_SHORT).show()
        } else {
            val dateTime = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.US).format(Date())
            val chat = Chat(message = message, dateTime = dateTime, image = "")
            listViewType.add(typeChat)
            listChat.add(chat)
            adapterChat.notifyDataSetChanged()
            input_message.setText("")
            // close keyboard after done
            var im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            im.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }


    }


    private fun setupAdapterRecyclerView() {
        listViewType = mutableListOf()
        listChat = mutableListOf()
        adapterChat = AdapterChat(listViewType = listViewType, listChat = listChat)
        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.adapter = adapterChat
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                requestCodeGallery -> {
                    val fileImage = getBitmapFile(data)
                    if (fileImage != null) {
                        val idTypeChat = radiogroup_chat.checkedRadioButtonId
                        val typeChat = if (idTypeChat == R.id.radioButton_myself) {
                            AdapterChat.VIEW_TYPE_MY_SELF
                        } else {
                            AdapterChat.VIEW_TYPE_USER
                        }
                        val message = ""
                        val dateTime = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.US)
                            .format(Date())
                        val chat = Chat(
                            message = message,
                            dateTime = dateTime,
                            image = fileImage.absolutePath
                        )
                        listViewType.add(typeChat)
                        listChat.add(chat)
                        adapterChat.notifyDataSetChanged()
                    }
                }
                requestCodeCamera -> {
                    val thumbnail = data?.extras!!["data"] as Bitmap
                    val baos = ByteArrayOutputStream()
                    thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                    val dir = File("${getExternalStorageDirectory()}/vviped")
                    if (!dir.exists()) {
                        dir.mkdirs()
                    }

                    try {
                        val fileImage = File(dir, "${Calendar.getInstance().timeInMillis}.jpg")
                        fileImage.createNewFile()
                        val fileOutputStream = FileOutputStream(fileImage)
                        fileOutputStream.write(baos.toByteArray())

                        MediaScannerConnection.scanFile(
                            this,
                            arrayOf(fileImage.path),
                            arrayOf("image/jpeg"),
                            null
                        )
                        fileOutputStream.close()

                        val idTypeChat = radiogroup_chat.checkedRadioButtonId
                        val typeChat = if (idTypeChat == R.id.radioButton_myself) {
                            AdapterChat.VIEW_TYPE_MY_SELF
                        } else {
                            AdapterChat.VIEW_TYPE_USER
                        }
                        val message = ""
                        val dateTime = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.US)
                            .format(Date())
                        val chat = Chat(
                            message = message,
                            dateTime = dateTime,
                            image = fileImage.absolutePath
                        )
                        listViewType.add(typeChat)
                        listChat.add(chat)
                        adapterChat.notifyDataSetChanged()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }


    private fun getBitmapFile(data: Intent?): File? {
        data?.let {
            val selectedImage = it.data
            val cursor = contentResolver
                .query(
                    selectedImage!!,
                    arrayOf(MediaStore.Images.ImageColumns.DATA),
                    null,
                    null,
                    null
                )
            cursor?.moveToFirst()

            val index = cursor?.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            val selectedImagePath = cursor?.getString(index!!)
            cursor?.close()
            return File(selectedImagePath)
        }
        return null
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.default_msg_1 -> {
                val message: String = default_msg_1.getText().toString()
                sendDefaultTextMessage(message)
            }
            R.id.default_msg_2 -> {
                val message: String = default_msg_2.getText().toString()
                sendDefaultTextMessage(message)
            }
            R.id.default_msg_3 -> {
                val message: String = default_msg_3.getText().toString()
                sendDefaultTextMessage(message)
            }
            R.id.default_msg_4 -> {
                val message: String = default_msg_4.getText().toString()
                sendDefaultTextMessage(message)
            }

        }
    }
}





