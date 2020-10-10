package com.sty.ne.modularlearning.base;

import com.sty.ne.common.RecordPathManager;
import com.sty.ne.common.base.BaseApplication;
import com.sty.ne.modularlearning.MainActivity;
import com.sty.ne.modularlearning.order.Order_MainActivity;
import com.sty.ne.modularlearning.personal.Personal_MainActivity;

/**
 * @Author: tian
 * @UpdateDate: 2020/10/10 10:45 PM
 */
public class AppApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        RecordPathManager.joinGroup("app", "MainActivity", MainActivity.class);
        RecordPathManager.joinGroup("order", "Order_MainActivity", Order_MainActivity.class);
        RecordPathManager.joinGroup("personal", "Personal_MainActivity", Personal_MainActivity.class);
    }
}
