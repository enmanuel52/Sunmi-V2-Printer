package com.example.sunmiv2printer

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.RemoteException
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sunmi.peripheral.printer.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        try{
//            val printer = SunmiPrinter.getInstance()
//            printer.initPrinter(applicationContext)
//
//            printer.printOriginalText("" +
//                    "\n" +
//                    "    He creado una carpeta libs en [su directorio de proyecto] \\ app \\ src\n" +
//                    "    He copiado jtwitter.jar (o el yambaclientlib.jar ) en el directorio [your project dir] \\ app \\ src \\ libs\n" +
//                    "    Al seguir la ruta de menú: Archivo -> Estructura del proyecto -> Dependencias -> Añadir -> Dependencia de archivos , Android Studio abre un cuadro de diálogo donde puede arrastrar y soltar la biblioteca del frasco . Luego hice clic en el botón Aceptar .\n", null)
//        } catch (e: Exception){
//            Log.d("LOG", "Error")
//        }

        val but = findViewById<Button>(R.id.button)
        but.setOnClickListener {
            try {
                print()
            } catch (e: RemoteException){
                e.printStackTrace()
                showToast("remote exception")
            }
        }

    }

    fun print() =
        InnerPrinterManager.getInstance().bindService(this, object : InnerPrinterCallback() {
            override fun onConnected(p0: SunmiPrinterService?) {
                p0?.setFontSize(12f, null)
                p0?.printText(" ${getString(R.string.app_name)} \t\t\t\t\t\t\t\t 27/5/2022\n" +
                        "${getString(R.string.ok)} \n",
                    object : InnerResultCallback() {
                        override fun onRunResult(p0: Boolean) {
                            if(p0)
                                Snackbar.make(findViewById(R.id.activity), "on run result", Snackbar.LENGTH_SHORT).show()
                            else
                                TODO("dghelthw")
                        }


                        override fun onReturnString(p0: String?) {
                            TODO("kljfhweoiwh")
                        }

                        override fun onRaiseException(p0: Int, p1: String?) {
                            TODO("457867867")
                        }

                        override fun onPrintResult(p0: Int, p1: String?) {
                            showToast("on print result")
                        }
                    })

            }

            override fun onDisconnected() {
                /**
                 *The method will be called back after the service is disconnected.A reconnection strategy is recommended here
                 */
            }
        })
    fun showToast(text: String) {
        Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
        Log.d("mensajes", text)
    }
}