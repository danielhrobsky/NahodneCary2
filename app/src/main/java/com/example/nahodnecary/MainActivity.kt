package com.example.nahodnecary


import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nahodnecary.ui.theme.NahodneCaryTheme


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()


        //setContentView(RotatingLineView(this));
        setContentView(R.layout.activity_main)


        val rotatingLineView = findViewById<RotatingLineView>(R.id.rotatingLineView)

        val buttonStart = findViewById<Button>(R.id.buttonStart)
        val buttonStop = findViewById<Button>(R.id.buttonStop)

        buttonStart.setOnClickListener {
            rotatingLineView.startRotation()
        }

        buttonStop.setOnClickListener {
            rotatingLineView.stopRotation()
        }


        /*setContent {
            NahodneCaryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }*/
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NahodneCaryTheme {
        Greeting("Android")
    }
}