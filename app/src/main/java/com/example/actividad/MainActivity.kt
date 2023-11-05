package com.example.actividad

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val spinnerCategory: Spinner = findViewById(R.id.spinnerCategory)
        val editTextAmount: EditText = findViewById(R.id.editTextAmount)
        val calculateButton: Button = findViewById(R.id.calculateButton)
        val textViewIGV: TextView = findViewById(R.id.IGV)
        val textViewAmountNoDiscount: TextView = findViewById(R.id.AmountNoDiscount)
        val textViewDiscountPercentage: TextView = findViewById(R.id.DiscountPercentage)
        val textViewAmountWithDiscount: TextView = findViewById(R.id.AmountWithDiscount)

        createCategoryProduct()

        calculateButton.setOnClickListener() {

            val selectedCategory = spinnerCategory.selectedItem.toString()
            val amount = editTextAmount.text.toString().toDouble()

            val discount = calculateDiscount(selectedCategory, amount)
            val igv = amount * 0.18
            val amountNoDiscount = amount
            val amountWithDiscount = amount - discount

            textViewIGV.text = "S/ ${String.format("%.2f", igv)}"

            textViewAmountNoDiscount.text = "S/ ${String.format("%.2f", amountNoDiscount)}"

            textViewDiscountPercentage.text =
                "% ${String.format("%.2f", (discount / amount * 100))}"

            textViewAmountWithDiscount.text = "S/ ${String.format("%.2f", amountWithDiscount)}"
        }

    }

    fun createCategoryProduct() {

        val spinnerCategory: Spinner = findViewById(R.id.spinnerCategory)

        val categories = listOf(
            "Zapatos",
            "Prendas para dama",
            "Electrodomésticos",
            "Celulares",
            "Ropa para caballero",
            "Juguetes para niños",
            "Laptops"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerCategory.adapter = adapter
    }

    fun calculateDiscount(category: String, amount: Double): Double {
        return when (category) {
            "Zapatos" -> return if (amount > 1000) amount * 0.10 else 0.0
            "Prendas para dama" -> return if (amount > 500) amount * 0.18 else 0.0
            "Electrodomésticos" -> return if (amount > 6000) amount * 0.07 else 0.0
            "Celulares" -> return if (amount > 3500) amount * 0.09 else 0.0
            "Ropa para caballero" -> return if (amount > 1500) amount * 0.05 else 0.0
            "Juguetes para niños" -> return if (amount > 2500) amount * 0.13 else 0.0
            "Laptops" -> return if (amount > 8000) amount * 0.19 else 0.0
            else -> {
                0.0
            }
        }
    }
}