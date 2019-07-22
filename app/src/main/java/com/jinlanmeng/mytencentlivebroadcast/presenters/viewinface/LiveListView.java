package com.jinlanmeng.mytencentlivebroadcast.presenters.viewinface;


import com.jinlanmeng.mytencentlivebroadcast.model.RoomInfoJson;
import com.jinlanmeng.mytencentlivebroadcast.presenters.UserServerHelper;

import java.util.ArrayList;

/**
 *  列表页面回调
 */
public interface LiveListView extends MvpView{


    void showRoomList(UserServerHelper.RequestBackInfo result, ArrayList<RoomInfoJson> roomlist);
}
