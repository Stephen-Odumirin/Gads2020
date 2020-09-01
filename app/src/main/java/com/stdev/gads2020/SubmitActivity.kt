package com.stdev.gads2020

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubmitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)

        val toolbar = findViewById<Toolbar>(R.id.submit_toolbar)
        setSupportActionBar(toolbar)

        val actionBar : ActionBar? = supportActionBar
        actionBar?.setDisplayShowCustomEnabled(true)
        val inflater : LayoutInflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val actionBarView = inflater.inflate(R.layout.custom_submit_toolbar,null)
        actionBar?.customView = actionBarView

        val firstName = findViewById<EditText>(R.id.submit_first_name)
        val lastName = findViewById<EditText>(R.id.submit_last_name)
        val emailAddress = findViewById<EditText>(R.id.submit_email_address)
        val githubProjectLink = findViewById<EditText>(R.id.submit_github_link)


        val buttonSubmit = findViewById<Button>(R.id.button_submit)
        buttonSubmit.setOnClickListener {
            if (firstName.text.isNotEmpty() || lastName.text.isNotEmpty() || emailAddress.text.isNotEmpty() || githubProjectLink.text.isNotEmpty()) {

                val alertDialog = AlertDialog.Builder(this)
                alertDialog.setMessage("Are you sure?")
                alertDialog.setCancelable(true)
                alertDialog.setPositiveButton(
                    "Yes",
                    DialogInterface.OnClickListener { dialog: DialogInterface?, which: Int ->
                        val name = firstName.text.toString()
                        val lastname = lastName.text.toString()
                        val emailaddress = emailAddress.text.toString()
                        val githublink = githubProjectLink.text.toString()
                        val submitService = GadsApi.retrofitSubmitService
                        val detailsCall =
                            submitService.submitProject(emailaddress, name, lastname, githublink)
                        detailsCall?.enqueue(object : Callback<Void?> {
                            override fun onFailure(call: Call<Void?>, t: Throwable) {
                                showToast("Error : ${t.message.toString()}")
                                val alertDialog1 = AlertDialog.Builder(this@SubmitActivity)
                                alertDialog1.setTitle(" ")
                                alertDialog1.setIcon(R.drawable.faliure_image)
                                alertDialog1.setMessage("Submission not Successful")
                                alertDialog1.create()
                                alertDialog1.show()
                            }

                            override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                                if (response.isSuccessful) {
                                    val alertDialog2 = AlertDialog.Builder(this@SubmitActivity)
                                    alertDialog2.setTitle(" ").setCancelable(true)
                                    alertDialog2.setIcon(R.drawable.success_image)
                                    alertDialog2.setMessage("Submission Successful ")
                                    alertDialog2.create()
                                    alertDialog2.show()
                                    showToast("Successful ! Hurray")
                                } else {
                                    val alertDialog3 = AlertDialog.Builder(this@SubmitActivity)
                                    alertDialog3.setTitle(" ").setCancelable(true)
                                    alertDialog3.setIcon(R.drawable.faliure_image)
                                    alertDialog3.setMessage("Submission not Successful")
                                    alertDialog3.create()
                                    alertDialog3.show()
                                    showToast("Opps Error, Try again")
                                }
                            }

                        })
                    })
                alertDialog.create()
                alertDialog.show()


            }
            else{
                Toast.makeText(this,"Input All Fields", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun showToast(message:String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}