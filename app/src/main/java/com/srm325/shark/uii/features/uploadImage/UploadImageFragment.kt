package com.srm325.shark.uii.features.uploadImage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.zxing.integration.android.IntentIntegrator
import com.srm325.shark.R
import com.srm325.shark.core.makeItGone
import com.srm325.shark.data.model.BarcodeResult
import kotlinx.android.synthetic.main.uploadimage_layout.*
import timber.log.Timber
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class UploadImageFragment : Fragment() {
    private  var type:TextView? = null
    private  lateinit var adapter : BarcodeResultAdapter
    private var contentList : MutableList<BarcodeResult> = mutableListOf()
    val db = Firebase.firestore
    val user = Firebase.auth.currentUser


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.uploadimage_layout, container, false)
        val scanBtn: ImageView = view.findViewById(R.id.scan_button)
        type = view.findViewById(R.id.type)
        scanBtn.setOnClickListener {
            IntentIntegrator.forSupportFragment(this).initiateScan();
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        doneButton.setOnClickListener {
            uploadData()
            scan_button.makeItGone()
            clickText.makeItGone()
            scanned_items_list.makeItGone()
            doneButton.makeItGone()
            Toast.makeText(context, "Recycling list uploaded!", Toast.LENGTH_SHORT).show()
        }

        adapter =  BarcodeResultAdapter(contentList)
        scanned_items_list.adapter = adapter

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        Timber.d("this is getting called")

        super.onActivityResult(requestCode, resultCode, intent)

        val scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent)
        if (scanningResult != null) {
            val scanContent = scanningResult.contents
            val scanFormat = scanningResult.formatName

            Timber.d(scanContent)
            Timber.d(scanFormat)
            if(scanContent!=null)
                readData(scanContent)
            
        } else {
            val toast = Toast.makeText(
                context,
                "No scan data received!", Toast.LENGTH_SHORT
            )
            toast.show()
        }
    }

    private fun readData(scannedValue: String) {
        Timber.d("CSV in read data method")
        var line: String?
        val reading = InputStreamReader(resources.openRawResource(R.raw.barcodedata))
        val reader = BufferedReader(reading)
        try {
            while (reader.readLine().also { line = it } != null) {
                if(line!=null){
                    Timber.d(line)
                    val matches = line!!.split(",")
                    val code = matches[0]
                    if(code==scannedValue) {
                        val name = matches[1]
                        val element = matches[2]
                        val isRecyclable = matches[3]
                        Toast.makeText(context, "Successful Match", Toast.LENGTH_SHORT).show()
                        contentList.add(
                            BarcodeResult(
                                "",
                                user!!.uid,
                                code,
                                name,
                                element,
                                isRecyclable
                            )
                        )
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun uploadData(){

        val tsLong = System.currentTimeMillis() / 1000
        val ts = tsLong.toString()

        for(item in contentList){

            item.id = ts
            db.collection("posts").document(ts)
                .set(item)
        }
    }
}