package quanticheart.com.cryptoshared

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.quanticheart.security.CryptoSharedPreferences
import com.quanticheart.security.DeviceID

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val c = CryptoSharedPreferences(this, "TESTES")
        val r = CryptoSharedPreferences(this, "TESTES", "APPKEY")
        val e = c.edit()
        e.putString("te", "var")
        e.commit()

        Log.e("Get On Crypto", c.getString("te", "ERROR!!!!") ?: "ERROR Instance")
        Log.e("ID", DeviceID.getUUID())
        Log.e("ID DEVICE", DeviceID.getAndroidID(baseContext))

    }
}
