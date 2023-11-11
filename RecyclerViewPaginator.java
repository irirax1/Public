package ir.aliyan.behsera.tool;

import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ir.aliyan.behsera.model.ToolViewModeInfo;

public abstract class RecyclerViewPaginator extends RecyclerView.OnScrollListener {

    /*
     * This is the Page Limit for each request
     * i.e. every request will fetch 19 transactions
     * */
    private int batchSize =20;

    /*
     * Variable to keep track of the current page
     * */
    private int currentPage = 0;

    /*
     * This variable is used to set
     * the threshold. For instance, if I have
     * set the page limit to 20, this will notify
     * the app to fetch more transactions when the
     * user scrolls to the 18th item of the list.
     * */
    private int threshold = 2;

    /*
     * This is a hack to ensure that the app is notified
     * only once to fetch more data. Since we use
     * scrollListener, there is a possibility that the
     * app will be notified more than once when user is
     * scrolling. This means there is a chance that the
     * same data will be fetched from the backend again.
     * This variable is to ensure that this does NOT
     * happen.
     * */
    private boolean endWithAuto = false;

    /*
     * We pass the RecyclerView to the constructor
     * of this class to get the LayoutManager
     * */
    private RecyclerView.LayoutManager layoutManager;
    public RecyclerViewPaginator(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(this);
        this.layoutManager = recyclerView.getLayoutManager();
    }
    boolean mIsLoading = false;
    boolean mIsLastPage = false;

    public boolean ismIsLoading() {
        return mIsLoading;
    }

    public void setmIsLoading(boolean mIsLoading) {
        this.mIsLoading = mIsLoading;
    }

    public boolean ismIsLastPage() {
        return mIsLastPage;
    }

    public void setmIsLastPage(boolean mIsLastPage) {
        this.mIsLastPage = mIsLastPage;
    }

    // amount of items you want to load per page
    final int pageSize = 20;
    ArrayList<ToolViewModeInfo> toolViewModeInfos;
    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
if (layoutManager!=null){

    int visibleItemCount = layoutManager.getChildCount();
    // number of items in layout
    int totalItemCount = layoutManager.getItemCount();
    // the position of first visible item
    int firstVisibleItemPosition = 0;
    if(layoutManager instanceof LinearLayoutManager) {
        firstVisibleItemPosition = ((LinearLayoutManager)layoutManager).findLastVisibleItemPosition();
    } else if(layoutManager instanceof GridLayoutManager) {
        firstVisibleItemPosition = ((GridLayoutManager)layoutManager).findLastVisibleItemPosition();
    }

    boolean isNotLoadingAndNotLastPage = !mIsLoading && !mIsLastPage;
    // flag if number of visible items is at the last
    boolean isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount;
    // validate non negative values
    boolean isValidFirstItem = firstVisibleItemPosition >= 0;
    // validate total items are more than possible visible items
    boolean totalIsMoreThanVisible = totalItemCount >= pageSize;
    // flag to know whether to load more
    boolean shouldLoadMore = isValidFirstItem && isAtLastItem && totalIsMoreThanVisible && isNotLoadingAndNotLastPage;

    if (shouldLoadMore) loadMoreItems(false);
}
    }


    public int getStartSize() {
        return ++currentPage;
    }

    public int getMaxSize() {
        return  currentPage + batchSize;
    }


    /*
     * I have added a reset method here
     * that can be called from the UI because
     * if we have a filter option in the app,
     * we might need to refresh the whole data set
     * */
    public void reset() {
        currentPage = 0;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
    }


    /*
     * I have created two abstract methods:
     * isLastPage() where the UI can specify if
     * this is the last page - this data usually comes from the backend.
     *
     * loadMore() where the UI can specify to load
     * more data when this method is called.
     *
     * We can also specify another method called
     * isLoading() - to let the UI display a loading View.
     * Since I did not need to display this, I have
     * commented it out.
     * */
    //public abstract boolean isLoading();

//    public abstract boolean isLastPage();
//    public abstract void loadMore(int start , int count);
    public abstract void loadMoreItems(boolean isFirstPage);
}
