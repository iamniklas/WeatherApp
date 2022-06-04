package de.niklasenglmeier.weatherapp


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class CompleteUserInterfaceTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun completeUserInterfaceTest() {
        val floatingActionButton = onView(
            allOf(
                withId(R.id.floatingActionButton),
                childAtPosition(
                    allOf(
                        withId(R.id.container),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        floatingActionButton.perform(click())

        val editText = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(androidx.appcompat.R.id.custom),
                        childAtPosition(
                            withId(androidx.appcompat.R.id.customPanel),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        editText.perform(replaceText("Rosenheim"), closeSoftKeyboard())

        val materialButton = onView(
            allOf(
                withId(android.R.id.button1), withText("OK"),
                childAtPosition(
                    childAtPosition(
                        withId(androidx.appcompat.R.id.buttonPanel),
                        0
                    ),
                    3
                )
            )
        )
        materialButton.perform(scrollTo(), click())

        val materialButton2 = onView(
            allOf(
                withId(android.R.id.button1), withText("Correct"),
                childAtPosition(
                    childAtPosition(
                        withId(androidx.appcompat.R.id.buttonPanel),
                        0
                    ),
                    3
                )
            )
        )
        materialButton2.perform(scrollTo(), click())

        val materialCardView = onView(
            allOf(
                withId(R.id.cardView_weather_card),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.recyclerView_fragment_home_weather_cards),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialCardView.perform(click())

        pressBack()

        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.navigation_location), withContentDescription("Locations"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_view),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())

        val bottomNavigationItemView2 = onView(
            allOf(
                withId(R.id.navigation_home), withContentDescription("Home"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_view),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView2.perform(click())

        val floatingActionButton2 = onView(
            allOf(
                withId(R.id.floatingActionButton),
                childAtPosition(
                    allOf(
                        withId(R.id.container),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        floatingActionButton2.perform(click())

        val editText2 = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(androidx.appcompat.R.id.custom),
                        childAtPosition(
                            withId(androidx.appcompat.R.id.customPanel),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        editText2.perform(replaceText("Hamburg"), closeSoftKeyboard())

        val materialButton3 = onView(
            allOf(
                withId(android.R.id.button1), withText("OK"),
                childAtPosition(
                    childAtPosition(
                        withId(androidx.appcompat.R.id.buttonPanel),
                        0
                    ),
                    3
                )
            )
        )
        materialButton3.perform(scrollTo(), click())

        val materialButton4 = onView(
            allOf(
                withId(android.R.id.button1), withText("Correct"),
                childAtPosition(
                    childAtPosition(
                        withId(androidx.appcompat.R.id.buttonPanel),
                        0
                    ),
                    3
                )
            )
        )
        materialButton4.perform(scrollTo(), click())

        val materialCardView2 = onView(
            allOf(
                withId(R.id.cardView_weather_card),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.recyclerView_fragment_home_weather_cards),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialCardView2.perform(click())

        pressBack()

        val bottomNavigationItemView3 = onView(
            allOf(
                withId(R.id.navigation_location), withContentDescription("Locations"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_view),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView3.perform(click())

        val bottomNavigationItemView4 = onView(
            allOf(
                withId(R.id.navigation_home), withContentDescription("Home"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_view),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView4.perform(click())

        val bottomNavigationItemView5 = onView(
            allOf(
                withId(R.id.navigation_location), withContentDescription("Locations"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_view),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView5.perform(click())

        val materialButton5 = onView(
            allOf(
                withId(R.id.button_location_delete), withText("Delete"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.cardView_location),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton5.perform(click())

        val bottomNavigationItemView6 = onView(
            allOf(
                withId(R.id.navigation_home), withContentDescription("Home"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_view),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView6.perform(click())

        val bottomNavigationItemView7 = onView(
            allOf(
                withId(R.id.navigation_location), withContentDescription("Locations"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_view),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView7.perform(click())

        val materialButton6 = onView(
            allOf(
                withId(R.id.button_location_delete), withText("Delete"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.cardView_location),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton6.perform(click())

        val bottomNavigationItemView8 = onView(
            allOf(
                withId(R.id.navigation_home), withContentDescription("Home"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_view),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView8.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
