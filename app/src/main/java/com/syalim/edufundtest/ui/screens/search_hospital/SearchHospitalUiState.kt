package com.syalim.edufundtest.ui.screens.search_hospital

import com.syalim.edufundtest.domain.model.Hospital


/**
 * Created by Rahmat Syalim on 2022/09/29
 * rahmatsyalim@gmail.com
 */

data class SearchHospitalUiState(
   val error: Throwable? = null,
   val data: List<Hospital>? = null,
   val isLoading: Boolean = false
)
