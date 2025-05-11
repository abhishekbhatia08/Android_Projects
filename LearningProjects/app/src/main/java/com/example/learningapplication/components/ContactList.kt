package com.example.learningapplication.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Contact(
    val name: String,
    val phoneNumber: String,
)

@Composable
fun ContactsList(modifier: Modifier = Modifier) {

    val contacts = remember {
        listOf(
            Contact(
                "Abhishek Bhatia",
                "9876543210"
            ),
            Contact(
                name = "John Doe",
                phoneNumber = "1234567890"
            ),
            Contact(
                name = "Jane Smith",
                phoneNumber = "9876543210"
            ),
            Contact(
                name = "Bob Johnson",
                phoneNumber = "5555555555"
            ),
            Contact(
                name = "Alice Brown",
                phoneNumber = "1112223333"
            ),
            Contact(
                "Abhishek Bhatia",
                "9876543210"
            ),
            Contact(
                name = "John Doe",
                phoneNumber = "1234567890"
            ),
            Contact(
                name = "Jane Smith",
                phoneNumber = "9876543210"
            ),
            Contact(
                name = "Bob Johnson",
                phoneNumber = "5555555555"
            ),
            Contact(
                name = "Alice Brown",
                phoneNumber = "1112223333"
            ),
            Contact(
                "Abhishek Bhatia",
                "9876543210"
            ),
            Contact(
                name = "John Doe",
                phoneNumber = "1234567890"
            ),
            Contact(
                name = "Jane Smith",
                phoneNumber = "9876543210"
            ),
            Contact(
                name = "Bob Johnson",
                phoneNumber = "5555555555"
            ),
            Contact(
                name = "Alice Brown",
                phoneNumber = "1112223333"
            )
        )

    }
    LazyColumn(modifier = modifier.systemBarsPadding()) {
        items(contacts) { contact ->
            ContactItem(contact = contact)
        }
    }
}

@Composable
fun ContactItem(contact: Contact, modifier: Modifier = Modifier) {
    Card(modifier = Modifier.fillMaxWidth().padding(all = 8.dp)) {
        Column(modifier = modifier.padding(12.dp)) {
            Text(text = contact.name)
            Text(text = contact.phoneNumber)
        }
    }
}