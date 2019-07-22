package com.jinlanmeng.mytencentlivebroadcast.presenters.viewinface;


import com.jinlanmeng.mytencentlivebroadcast.model.MemberInfo;

import java.util.ArrayList;

/**
 * 成员列表回调
 */
public interface MembersDialogView extends MvpView {

    void showMembersList(ArrayList<MemberInfo> data);

}
