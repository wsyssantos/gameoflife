package com.quandoo.challenge.gameoflife;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.GridView;

import com.quandoo.challenge.gameoflife.adapter.CellAdapter;


import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by wsyssantos on 6/28/16.
 */
public class GameOfLifeActivityTest extends ActivityInstrumentationTestCase2<GameOfLifeActivity> {

    private GameOfLifeActivity testGameOfLifeActivity;
    private GridView testGridView;


    public GameOfLifeActivityTest() {
        super(GameOfLifeActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        testGameOfLifeActivity = getActivity();

        testGridView = (GridView) testGameOfLifeActivity.findViewById(R.id.cellGrid);
    }

    public void testGameOfLifeWithOneGeneration() {
        onData(anything()).inAdapterView(withId(R.id.cellGrid)).atPosition(0).perform(click());

        onView(withId(R.id.imgButtonPlay)).perform(click());

        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }

        onView(withId(R.id.imgButtonStop)).perform(click());
        int[][] result = ((CellAdapter)testGridView.getAdapter()).getGeneration();
        assertThat(result[0][0], is(0));
    }


    public void testGameOfLifeWithInfiniteForm() {
        onData(anything()).inAdapterView(withId(R.id.cellGrid)).atPosition(1).perform(click());
        onData(anything()).inAdapterView(withId(R.id.cellGrid)).atPosition(20).perform(click());
        onData(anything()).inAdapterView(withId(R.id.cellGrid)).atPosition(21).perform(click());

        onView(withId(R.id.imgButtonPlay)).perform(click());

        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }

        onView(withId(R.id.imgButtonStop)).perform(click());
        int[][] result = ((CellAdapter)testGridView.getAdapter()).getGeneration();
        assertThat(result[0][0], is(1));
    }
}
