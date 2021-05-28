package hr.ferit.nikoladanilovic.soundboard

import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import hr.ferit.nikoladanilovic.soundboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var mSoundPool: SoundPool
    private var mLoaded: Boolean = false
    var mSoundMap: HashMap<Int, Int> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        mainBinding.firstImage.setOnClickListener { onClickFirstImage() }
        mainBinding.secondImage.setOnClickListener { onClickSecondImage() }
        mainBinding.thirdImage.setOnClickListener { onClickThirdImage() }
        setContentView(mainBinding.root)
        this.loadSounds()
    }

    private fun onClickFirstImage() {
        if (this.mLoaded == false) return
        playSound(R.raw.elonmusk)
    }

    private fun onClickSecondImage() {
        if (this.mLoaded == false) return
        playSound(R.raw.linustorwalds)
    }

    private fun onClickThirdImage() {
        if (this.mLoaded == false) return
        playSound(R.raw.jeffbezos)
    }

    private fun loadSounds() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.mSoundPool = SoundPool.Builder().setMaxStreams(10).build()
        } else {
            this.mSoundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0)
        }
        this.mSoundPool.setOnLoadCompleteListener { _, _, _ -> mLoaded = true }
        this.mSoundMap[R.raw.elonmusk] = this.mSoundPool.load(this, R.raw.elonmusk, 1)
        this.mSoundMap[R.raw.linustorwalds] = this.mSoundPool.load(this, R.raw.linustorwalds, 1)
        this.mSoundMap[R.raw.jeffbezos] = this.mSoundPool.load(this, R.raw.jeffbezos, 1)
    }

    fun playSound(selectedSound: Int) {
        val soundID = this.mSoundMap[selectedSound] ?: 0
        this.mSoundPool.play(soundID, 1f, 1f, 1, 0, 1f)
    }
}