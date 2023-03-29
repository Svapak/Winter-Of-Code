package com.example.loginandsignup.Repository

import androidx.lifecycle.MutableLiveData
import com.example.loginandsignup.Models.Item
import com.google.firebase.database.*

class FoundItemRepository {

    private val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("found")

    @Volatile
    private var INSTANCE: FoundItemRepository? = null

    fun getInstance(): FoundItemRepository {
        return INSTANCE ?: synchronized(this) {

            val instance = FoundItemRepository()
            INSTANCE = instance
            instance

        }
    }

    fun loadItems(itemList: MutableLiveData<List<Item>>) {

        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                try {
                    val _itemList : List<Item> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(Item::class.java)!!
                    }
                    itemList.postValue(_itemList)
                }catch (e : Exception){

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })


    }


}

