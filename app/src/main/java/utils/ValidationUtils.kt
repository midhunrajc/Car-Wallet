package utils

object ValidationUtils {

    fun isValidBrand(brand: String?): Boolean {
        return !brand.isNullOrBlank() && brand.length >= Constants.MIN_BRAND_LENGTH
    }

    fun isValidModel(model: String?): Boolean {
        return !model.isNullOrBlank() && model.length >= Constants.MIN_MODEL_LENGTH
    }

    fun isValidFuelType(fuelType: String?): Boolean {
        return !fuelType.isNullOrBlank()
    }
}
