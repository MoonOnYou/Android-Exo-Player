package com.example.exoplayercustom

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.activity_player.*

/**
 *   참고 블로그
 *   https://selfish-developer.com/entry/Exoplayer2-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0
 *   https://lcw126.tistory.com/117
 */
class PlayerActivity : AppCompatActivity() {
    private val videoUri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4")
    private var player : SimpleExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

    }

    override fun onStart() {
        super.onStart()
        // simple exo player 객체 생성
        player = ExoPlayerFactory.newSimpleInstance(this, DefaultTrackSelector())

        // 뷰에 플레이어 설정
        playerView?.player = player
        playerControlView?.player = player

        //비디오데이터 소스를 관리하는 DataSource 객체를 만들어주는 팩토리 객체 생성
        val factory = DefaultDataSourceFactory(this, this.applicationInfo.packageName)
        //비디오데이터를 Uri로 부터 추출해서 DataSource객체 (CD or LP판 같은 ) 생성
        val mediaSource = ExtractorMediaSource.Factory(factory).createMediaSource(videoUri)


        player?.prepare(mediaSource)
//        player?.playWhenReady = true // 준비 됬을 때 동영상 바로 실행 할건지 여부
    }

    override fun onStop() {
        super.onStop()
        playerView?.player = null
        player?.release()
        player = null
    }
}