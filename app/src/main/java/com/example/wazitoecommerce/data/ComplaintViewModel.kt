package com.example.wazitoecommerce.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.wazitoecommerce.models.Complaint
import com.example.wazitoecommerce.navigation.LOGIN_URL
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class ComplaintViewModel(var navController:NavHostController, var context: Context) {
    var authViewModel:AuthViewModel
    var progress:ProgressDialog
    init {
        authViewModel = AuthViewModel(navController, context)
        if (!authViewModel.isLoggedIn()){
            navController.navigate(LOGIN_URL)
        }
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }

    fun uploadComplaint(nature:String, impact:String, location:String, filePath:Uri){
        val complaintId = System.currentTimeMillis().toString()
        val storageRef = FirebaseStorage.getInstance().getReference()
            .child("Complaints/$complaintId")
        progress.show()
        storageRef.putFile(filePath).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                // Save data to db
                storageRef.downloadUrl.addOnSuccessListener {
                    var imageUrl = it.toString()
                    var complaint = Complaint(nature,impact,location,imageUrl,complaintId)
                    var databaseRef = FirebaseDatabase.getInstance().getReference()
                        .child("Complaints/$complaintId")
                    databaseRef.setValue(complaint).addOnCompleteListener {
                        if (it.isSuccessful){
                            Toast.makeText(this.context, "Success", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this.context, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }else{
                Toast.makeText(this.context, "Upload error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun allComplaints(
        complaint:MutableState<Complaint>,
        complaints:SnapshotStateList<Complaint>):SnapshotStateList<Complaint>{
        progress.show()
        var ref = FirebaseDatabase.getInstance().getReference()
            .child("Complaints")
        ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                complaints.clear()
                for (snap in snapshot.children){
                    var retrievedComplaint = snap.getValue(Complaint::class.java)
                    complaint.value = retrievedComplaint!!
                    complaints.add(retrievedComplaint)
                }
                progress.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "DB locked", Toast.LENGTH_SHORT).show()
            }
        })
        return complaints
    }

    fun deleteComplaint(complaintId:String){
        var ref = FirebaseDatabase.getInstance().getReference()
            .child("Complaints/$complaintId")
        ref.removeValue()
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }
}