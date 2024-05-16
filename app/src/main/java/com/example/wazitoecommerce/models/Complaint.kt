package com.example.wazitoecommerce.models

class Complaint {
    var nature:String = ""
    var location:String = ""
    var impact:String = ""
    var imageUrl:String = ""
    var id:String = ""

    constructor(nature: String, impact: String, location: String, imageUrl: String, id: String) {
        this.nature = nature
        this.impact= impact
        this.location= location
        this.imageUrl = imageUrl
        this.id = id
    }

    constructor()

}