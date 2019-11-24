package me.ghsong.dice

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var diceTextView: TextView
    private lateinit var diceImageView: ImageView
    // 주사위 번호를 입력받는다.
    private lateinit var diceNumberInputView: EditText
    // 닉네임을 입력받는다.
    private lateinit var nickNameInputView: EditText

    // 버튼이 몇번 눌렸는지 횟수를 샌다.
    var count = 0

    // 주사위를 맞춘 횟수를 기록한다.
    var matchCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        diceTextView = findViewById(R.id.tvDice)
        diceTextView.setText("코드에서 변경한 텍스트")

        diceImageView = findViewById(R.id.imageView)

        diceNumberInputView = findViewById(R.id.editText)

        nickNameInputView = findViewById(R.id.editText2)
    }

    fun onRollClick(v: View) {

        // 닉네임이 유효한지 확인한다.
        if(nickNameInputView.getText().toString().isEmpty()) {
            showToast("닉네임을 입력해주세요.")
            return
        }

        // 닉네임 변경 불가
        nickNameInputView.isEnabled = false

        count++

        if (count > 5) {

        } else {

            var diceNumber = Random.nextInt(1, 7)

            diceTextView.setText("주사위 번호 : $diceNumber")

            when (diceNumber) {
                1 -> diceImageView.setImageResource(R.drawable.dice_1)
                2 -> diceImageView.setImageResource(R.drawable.dice_2)
                3 -> diceImageView.setImageResource(R.drawable.dice_3)
                4 -> diceImageView.setImageResource(R.drawable.dice_4)
                5 -> diceImageView.setImageResource(R.drawable.dice_5)
                6 -> diceImageView.setImageResource(R.drawable.dice_6)
            }

            // 입력한 값을 가져온다.
            var inputValue = diceNumberInputView.getText().toString()

            if (inputValidatationCheck(inputValue)) {
                // 주사위와 입력한 값이 같은지 검사한다.
                if (diceNumber === inputValue.toInt()) {
                    showToast("정답입니다.")
                    matchCount++
                } else {
                    showToast("아쉽네요. 다음에 다시 도전해주세요.")
                }
            } else {
                showToast("입력값이 잘못되었습니다.")
//                Log.e("MainActivity", "입력값이 잘못되었습니다.")
            }

            if (count === 5) {
                showToast(
                    if (matchCount >= 2) "성공했습니다." else "실패했습니다."
                )
                var resultIntent = Intent(this, ResultActivity::class.java)
                resultIntent.putExtra(ResultActivity.KEY_RESULT, matchCount)
                resultIntent.putExtra(ResultActivity.KEY_NICKNAME, nickNameInputView.getText().toString())
                startActivity(resultIntent)
                finish()
            }
        }
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * 1~6 까지인지, 숫자인지 확인한다.
     * @return true or false
     */
    fun inputValidatationCheck(inputValue: String): Boolean {
        try {
            // 입력값이 없는 경우
            if (inputValue.isEmpty()) return false
            // 입력값이 1보다 작거나 6보다 큰 경우
            else if (inputValue.toInt() < 1 || inputValue.toInt() > 6) return false
        } catch (e: Exception) {
            // 숫자가 입력하지 않은 경우
            return false
        }
        return true
    }

}
