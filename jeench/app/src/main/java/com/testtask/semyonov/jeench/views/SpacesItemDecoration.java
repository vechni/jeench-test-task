package com.testtask.semyonov.jeench.views;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacesItemDecoration
        extends RecyclerView.ItemDecoration
{

    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;
    private static final int GRID = 2;

    private final int mTop;
    private final int mRight;
    private final int mLeft;
    private final int mBottom;

    public SpacesItemDecoration( final int top, final int right, final int left, final int bottom ){
        mTop = top;
        mRight = right;
        mLeft = left;
        mBottom = bottom;
    }

    @Override
    public void getItemOffsets( final Rect outRect, final View view, final RecyclerView parent,
                                final RecyclerView.State state ){
        final int position = parent.getChildViewHolder(view).getAdapterPosition();
        final int itemCount = state.getItemCount();
        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        setSpacingForDirection(outRect, layoutManager, position, itemCount);
    }

    /* https://gist.github.com/alexfu/f7b8278009f3119f523a */
    private void setSpacingForDirection( @NonNull final Rect outRect,
                                         @NonNull final RecyclerView.LayoutManager layoutManager,
                                         final int position,
                                         final int itemCount ){
        /* Resolve display mode automatically */
        final int displayMode = resolveDisplayMode(layoutManager);

        switch( displayMode ){
            case HORIZONTAL:
                outRect.left = mLeft;
                outRect.right = position == itemCount - 1
                        ? mRight
                        : 0;
                outRect.top = mTop;
                outRect.bottom = mBottom;
                break;
            case VERTICAL:
                outRect.left = mLeft;
                outRect.right = mRight;
                outRect.top = mTop;
                outRect.bottom = position == itemCount - 1
                        ? mBottom
                        : 0;
                break;
            case GRID:
                if( layoutManager instanceof GridLayoutManager ){
                    final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                    final int cols = gridLayoutManager.getSpanCount();
                    final int rows = itemCount / cols;

                    outRect.left = mLeft;
                    outRect.right = position % cols == cols - 1
                            ? mRight
                            : 0;
                    outRect.top = mTop;
                    outRect.bottom = position / cols == rows - 1
                            ? mBottom
                            : 0;
                }
                break;
        }
    }

    private int resolveDisplayMode( final RecyclerView.LayoutManager layoutManager ){
        if( layoutManager instanceof GridLayoutManager ){
            return GRID;
        }
        if( layoutManager.canScrollHorizontally() ){
            return HORIZONTAL;
        }
        return VERTICAL;
    }
}
