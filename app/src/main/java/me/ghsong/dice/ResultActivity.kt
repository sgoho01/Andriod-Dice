package me.ghsong.dice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private lateinit var nicknameTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        resultTextView = findViewById(R.id.tvResult)
        nicknameTextView = findViewById(R.id.tvNickname)

        // 결과를 전달 받는다.
        var result = getIntent().getIntExtra(KEY_RESULT, 0)
        var nickname = getIntent().getStringExtra(KEY_NICKNAME)

        // 결과를 출력한다.
        resultTextView.setText("$result")
        nicknameTextView.setText(nickname)
    }

    // 상수 선언
    companion object {
        // 주사위 결과
        const val KEY_RESULT = "KEY_RESULT"
        // 닉네임
        const val KEY_NICKNAME = "KEY_NICKNAME"
    }
}
