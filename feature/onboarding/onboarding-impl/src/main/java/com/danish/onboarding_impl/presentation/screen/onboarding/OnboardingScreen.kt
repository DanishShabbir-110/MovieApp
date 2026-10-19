package com.danish.onboarding_impl.presentation.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danish.onboarding_impl.presentation.model.onboardingPages
import com.danish.shared.components.AppButton
import com.danish.shared.components.AppText
import com.danish.shared.components.AppTextButton
import com.danish.shared.components.PageIndicator
import com.danish.shared.theme.AppColors
import com.danish.shared.theme.AppTextStyles
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    state: OnboardingState,
    onEvent: (OnboardingEvent) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { onboardingPages.size })
    val coroutineScope = rememberCoroutineScope()
    val isLastPage = pagerState.currentPage == onboardingPages.lastIndex

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            val onboardingPage = onboardingPages[page]

            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = onboardingPage.imageRes),
                    contentDescription = onboardingPage.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Transparent,
                                    AppColors.Background.copy(alpha = 0.20f),
                                    AppColors.Background.copy(alpha = 0.60f),
                                    AppColors.Background.copy(alpha = 0.90f),
                                    AppColors.Background
                                )
                            )
                        )
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(horizontal = 28.dp)
                        .padding(bottom = 150.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AppText(
                        text = onboardingPage.title,
                        style = AppTextStyles.Display,
                        textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.height(12.dp))

                    AppText(
                        text = onboardingPage.description,
                        style = AppTextStyles.Body.copy(fontSize = 16.sp),
                        color = AppColors.TextSecondary,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
        ) {
            PageIndicator(
                pageCount = onboardingPages.size,
                currentPage = pagerState.currentPage,
                modifier = Modifier.fillMaxWidth(),
                activeColor = AppColors.Info,
                inactiveColor = AppColors.IndicatorInactive,
                dotSize = 8.dp,
                activeWidth = 24.dp,
                spacing = 8.dp
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppTextButton(
                    text = "Skip",
                    onClick = { onEvent(OnboardingEvent.Completed) }
                )

                AppButton(
                    text = if (isLastPage) "Get Started" else "Next",
                    onClick = {
                        if (isLastPage) {
                            onEvent(OnboardingEvent.Completed)
                        } else {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    },
                    enabled = !state.isLoading,
                    containerColor = AppColors.Info,
                    shape = RoundedCornerShape(24.dp),
                    contentPadding = PaddingValues(horizontal = 36.dp, vertical = 12.dp)
                )
            }
        }
    }
}