import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.R
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener{ calculateTip() }
    }


        private fun calculateTip() {
            val stringInTextField = binding.custoDoServico.text.toString()
            val cost = stringInTextField.toDoubleOrNull()
            if (cost == null) {
                binding.tipResult.text = ""
                return
            }
            val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
                R.id.opcao_surpreendente -> 0.20
                R.id.opcao_bom -> 0.18
                else -> 0.15
            }

            var tip = tipPercentage * cost
            if (binding.roundUpSwitch.isChecked) {
                tip = kotlin.math.ceil(tip)
            }
            val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
            binding.tipResult.text = getString(R.string.valor_gorjeta, formattedTip)

    }
}
