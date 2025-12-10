import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var etCity: EditText
    private lateinit var btnGetWeather: Button
    private lateinit var tvCity: TextView
    private lateinit var tvWeather: TextView

    private val apiKey = "73816bdf8dda4fff027f8288fbd5bb57"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etCity = findViewById(R.id.etCity)
        btnGetWeather = findViewById(R.id.btnGetWeather)
        tvCity = findViewById(R.id.tvCity)
        tvWeather = findViewById(R.id.tvWeather)

        btnGetWeather.setOnClickListener {
            val city = etCity.text.toString().trim()
            if (city.isNotEmpty()) {
                fetchWeather(city)
            } else {
                tvWeather.text = "도시를 입력해주세요"
            }
        }
    }

    private fun fetchWeather(city: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(WeatherService::class.java)
        val call = service.getWeather(city, apiKey)

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    val weather = response.body()
                    tvCity.text = city
                    tvWeather.text = "${weather?.weather?.get(0)?.description}, ${weather?.main?.temp}°C"
                } else {
                    tvWeather.text = "날씨 정보를 가져올 수 없습니다"
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                tvWeather.text = "서버 연결 실패"
            }
        })
    }
}
