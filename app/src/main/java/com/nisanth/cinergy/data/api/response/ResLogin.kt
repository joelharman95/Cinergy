package com.nisanth.cinergy.data.api.response

import com.google.gson.annotations.SerializedName

data class ResLogin(
    @SerializedName("response") val response: String? = null,
    @SerializedName("splash_images") val splashImages: SplashImages? = null,
    @SerializedName("food_menu") val foodMenu: Int? = null,
    @SerializedName("attractions_menu") val attractionsMenu: Int? = null,
    @SerializedName("user") val user: User? = null
)

data class User(
    @SerializedName("birthday") val birthday: Any? = null,
    @SerializedName("gender") val gender: Any? = null,
    @SerializedName("city") val city: Any? = null,
    @SerializedName("created_at") val createdAt: String? = null,
    @SerializedName("opt_out2") val optOut2: Int? = null,
    @SerializedName("location_id") val locationId: Any? = null,
    @SerializedName("ip_access") val ipAccess: Any? = null,
    @SerializedName("user_role") val userRole: Int? = null,
    @SerializedName("opt_out1") val optOut1: Int? = null,
    @SerializedName("updated_at") val updatedAt: String? = null,
    @SerializedName("role_id") val roleId: Int? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("first_name") val firstName: Any? = null,
    @SerializedName("email") val email: Any? = null,
    @SerializedName("phone_code") val phoneCode: String? = null,
    @SerializedName("encrypted_password") val encryptedPassword: Any? = null,
    @SerializedName("member_level_name") val memberLevelName: String? = null,
    @SerializedName("zip") val zip: Any? = null,
    @SerializedName("member_id") val memberId: Any? = null,
    @SerializedName("booking_token") val bookingToken: Any? = null,
    @SerializedName("image") val image: Any? = null,
    @SerializedName("is_active") val isActive: Int? = null,
    @SerializedName("address2") val address2: Any? = null,
    @SerializedName("address1") val address1: Any? = null,
    @SerializedName("last_name") val lastName: Any? = null,
    @SerializedName("subscribed_member") val subscribedMember: Int? = null,
    @SerializedName("deleted_at") val deletedAt: Any? = null,
    @SerializedName("cinema_id") val cinemaId: Any? = null,
    @SerializedName("access_token") val accessToken: Any? = null,
    @SerializedName("location_name") val locationName: String? = null,
    @SerializedName("phone") val phone: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("fb_login") val fbLogin: Any? = null,
    @SerializedName("gmail_login") val gmailLogin: Any? = null,
    @SerializedName("username") val username: String? = null
)

data class SplashImages(
    @SerializedName("splash_image_bottom") val splashImageBottom: String? = null,
    @SerializedName("splash_image_top") val splashImageTop: String? = null
)
