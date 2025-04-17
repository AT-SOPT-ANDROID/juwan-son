package org.sopt.at.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import kotlinx.coroutines.launch
import org.sopt.at.R
import org.sopt.at.data.remote.model.LiveItem
import javax.inject.Inject

// Inject annotation을 달 것
class HomeViewModel @Inject constructor(

): ViewModel() {

    // BottomNavBar의 선택된 아이템 상태
    private val _selectedItem = mutableStateOf("HOME")
    val selectedItem: State<String> = _selectedItem

    // 실시간 인기 LIVE 항목 리스트 상태
    private val _liveItems = mutableStateOf<List<LiveItem>>(emptyList())
    val liveItems: State<List<LiveItem>> = _liveItems

    // 배너 아이템 리스트 상태
    private val _bannerItems = mutableStateOf<List<Int>>(emptyList())
    val bannerItems: State<List<Int>> = _bannerItems

    // BottomNavBar 아이템 선택 시 처리하는 함수
    fun onItemSelected(item: String) {
        _selectedItem.value = item
    }

    // 실시간 인기 LIVE 항목을 가져오는 함수
    fun fetchLiveItems() {
        viewModelScope.launch {
            val items = listOf(
                LiveItem(1, "스누피", R.drawable.img_live_1),
                LiveItem(2, "스누피", R.drawable.img_live_2),
                LiveItem(3, "스누피", R.drawable.img_live_3),
                LiveItem(4, "스누피", R.drawable.img_live_4)
            )
            _liveItems.value = items
        }
    }

    // 배너 이미지 데이터 가져오는 함수
    fun fetchBannerItems() {
        viewModelScope.launch {
            val banners = listOf(
                R.drawable.ic_banner_1,
                R.drawable.ic_banner_2,
                R.drawable.ic_banner_3,
                R.drawable.ic_banner_4,
                R.drawable.ic_banner_5
            )
            _bannerItems.value = banners
        }
    }
}
