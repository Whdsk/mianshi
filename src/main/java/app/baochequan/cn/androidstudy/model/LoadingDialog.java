package app.baochequan.cn.androidstudy.model;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import app.baochequan.cn.androidstudy.R;


/**
 * author gaohangbo
 * date: 2018/7/6 0006.
 */
public class LoadingDialog {
    /** 加载数据对话框 */
    private static Dialog mLoadingDialog;
//    private static AnimationDrawable animationDrawable;
    /**
     * 显示加载对话框
     * @param context 上下文
     * @param msg 对话框显示内容
     * @param cancelable 对话框是否可以取消
     */
    public static Dialog showDialogForLoading(Activity context, String msg, boolean cancelable) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null,false);
//        TextView loadingText = (TextView)view.findViewById(R.id.id_tv_loading_dialog_text);
        LinearLayout loadingCon = (LinearLayout)view.findViewById(R.id.loading_container);
        ImageView animationIV= view.findViewById(R.id.iv_loading);
//        animationDrawable = (AnimationDrawable) animationIV.getDrawable();
//        animationDrawable.start();
        Glide.with(context).load(R.mipmap.loading).asGif().into(animationIV);

        /*if (!TextUtils.isEmpty(msg)){
            loadingText.setText(msg);
        }else{
            loadingText.setText("加载中...");
        }*/

        mLoadingDialog = new Dialog(context, R.style.Translucent_NoTitle);
        mLoadingDialog.setCancelable(cancelable);
        mLoadingDialog.setCanceledOnTouchOutside(false);
        mLoadingDialog.setContentView(view/*, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)*/);

        Window dialogWindow = mLoadingDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);


        //获取手机屏幕的长和宽
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int scWidth = wm.getDefaultDisplay().getWidth();
//
//                lp.x = 100; // 新位置X坐标
//                lp.y = 100; // 新位置Y坐标
        lp.width = scWidth/3; // 宽度
        lp.height = lp.width; // 高度
        lp.alpha = 0.8f; // 透明度

        // 当Window的Attributes改变时系统会调用此函数,可以直接调用以应用上面对窗口参数的更改,也可以用setAttributes
        // dialog.onWindowAttributesChanged(lp);
        dialogWindow.setAttributes(lp);

        mLoadingDialog.show();
        return  mLoadingDialog;
    }

    public static Dialog showDialogForLoading(Activity context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        ImageView animationIV= view.findViewById(R.id.iv_loading);
//        animationDrawable = (AnimationDrawable) animationIV.getDrawable();
//        animationDrawable.start();
        Glide.with(context).load(R.mipmap.loading).asGif().into(animationIV);

        /*TextView loadingText = (TextView)view.findViewById(R.id.id_tv_loading_dialog_text);
        loadingText.setText("加载中...");*/

        mLoadingDialog = new Dialog(context, R.style.Translucent_NoTitle);
        mLoadingDialog.setCancelable(true);
        mLoadingDialog.setCanceledOnTouchOutside(false);
        mLoadingDialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        mLoadingDialog.show();
        return  mLoadingDialog;
    }

    /**
     * 关闭加载对话框
     */
    public static void cancelDialogForLoading() {
        if(mLoadingDialog != null) {
            mLoadingDialog.cancel();
        }
        /*if(animationDrawable!=null) {
            animationDrawable.stop();
        }*/
    }
}
