package com.devappsys.materialnavmenu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Shashanth on 03 Aug 2016.
 * DevAppSys IT Solutions Pvt. Ltd
 * shashanth@devappsys.com
 */
public class NavDrawerRecyclerAdapter extends RecyclerView.Adapter<NavDrawerRecyclerAdapter.NavViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private Context context;
    private String titles[];
    private int icons[];
    private int lastPosition = -1;

    public NavDrawerRecyclerAdapter(Context context, String titles[], int icons[]) {
        this.context = context;
        this.titles = titles;
        this.icons = icons;
    }

    @Override
    public NavViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_drawer_row, parent, false);
            return new NavViewHolder(view, viewType);
        } else if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_drawer_header, parent, false);
            return new NavViewHolder(view, viewType);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(NavViewHolder holder, int position) {
        if (holder.holderID == TYPE_ITEM) {
            holder.txtMenuName.setText(titles[position - 1]);
            holder.imgMenuIcon.setImageResource(icons[position - 1]);
            setAnimation(holder.txtMenuName, holder.imgMenuIcon, position);
        }
    }


    @Override
    public int getItemCount() {
        return titles.length + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        } else {
            return TYPE_ITEM;
        }
    }

    public class NavViewHolder extends RecyclerView.ViewHolder {

        int holderID;
        public TextView txtMenuName;
        public ImageView imgMenuIcon;

        public NavViewHolder(View itemView, int viewType) {
            super(itemView);

            if (viewType == TYPE_ITEM) {
                txtMenuName = (TextView) itemView.findViewById(R.id.nav_item_title);
                imgMenuIcon = (ImageView) itemView.findViewById(R.id.nav_icon);
                holderID = TYPE_ITEM;
            } else {
                holderID = TYPE_HEADER;
            }
        }
    }

    private boolean isPositionHeader(int position) {
        return position == TYPE_HEADER;
    }

    private void setAnimation(TextView textView, ImageView imageView, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            textView.startAnimation(animation);
            imageView.startAnimation(animation);
            lastPosition = position;
        }
    }
}
