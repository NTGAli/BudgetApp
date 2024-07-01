package com.ntg.feature.home

import androidx.lifecycle.ViewModel
import com.ntg.core.data.repository.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
//    private val analyticsHelper: AnalyticsHelper,
  private val cardRepository: CardRepository
): ViewModel() {

  val selectedCard = cardRepository.getSelectedCategory()

}
