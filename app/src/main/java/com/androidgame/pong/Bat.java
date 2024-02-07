package com.androidgame.pong;

import android.graphics.RectF;

public class Bat {

    private RectF mRect;
    private float mLength;
    private float mXCoord;
    private float mBatSpeed;
    private int mScreenX;

    final int STOPPED = 0;
    final int LEFT = 1;
    final int RIGHT = 2;

    // Keeps track of if and how the bat is moving
    // Starting with STOPPED condition
    private int mBatMoving = STOPPED;

    public Bat(int sx, int sy) {
        // Bat needs to know the screen horizontal resolution
        // outside of this method
        mScreenX = sx;

        // Configure the size of the bat based on the screen resolution
        // 1/8 of the screen width
        mLength = mScreenX / 8;

        // 1/40 of the screen height
        float height = sy / 40;

        // Configure the starting location of the bat
        // Roughly the middle horizontally
        mXCoord = mScreenX / 2;

        // The height of the bat off the bottom of the screen
        float mYCoord = sy - height;

        // Initialize mRect based on the size and position
        mRect = new RectF(mXCoord, mYCoord,
                mXCoord + mLength,
                mYCoord + height);

        // Configure the speed of the bat
        // This code means the bat con cover the width of the screen in 1 second
        mBatSpeed = mScreenX;
    }

    // Return a reference to the mRect object
    RectF getRect() {
        return mRect;
    }

    // Update the movement state passed in by the onTouchEvent method
    void setMovementState(int state) {
        mBatMoving = state;
    }

    // Update the bat - Called each frame/loop
    void update(long fps) {
        // Move the bat based on the mBatMoving variable
        // and the speed of the previous frame
        if (mBatMoving == LEFT) {
            mXCoord = mXCoord - mBatSpeed / fps;
        }

        if (mBatMoving == RIGHT) {
            mXCoord = mXCoord + mBatSpeed / fps;
        }

        // Stop the bat going off the screen
        if (mXCoord < 0) {
            mXCoord = 0;
        } else if (mXCoord + mLength > mScreenX) {
            mXCoord = mScreenX - mLength;
        }

        // Update mRect based on the results from the previous code in update
        mRect.left = mXCoord;
        mRect.right = mXCoord + mLength;
    }
}
