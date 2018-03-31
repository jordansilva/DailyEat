package app.jordansilva.data.model

data class RecipeIngredientModel(val name: String,
                            val amount: Float,
                            val amountType: String) : BaseModel()