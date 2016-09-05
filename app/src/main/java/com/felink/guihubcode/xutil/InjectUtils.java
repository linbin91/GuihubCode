package com.felink.guihubcode.xutil;

import android.app.Activity;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2016/7/22.
 */
public class InjectUtils {
    /**
     * 注入
     * @param activity
     */
    public static void inject(Activity activity) {
        injectLayout(activity);
        injectViews(activity);
        injectEvents(activity);
    }

    private static void injectEvents(Activity activity) {
        Class<?> clazz = activity.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods){
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation :annotations){
                Class<? extends  Annotation> annotationType = annotation.annotationType();
                EventBase eventBaseAnnotation = annotationType.getAnnotation(EventBase.class);
                if (eventBaseAnnotation != null){
                    String listenerSetter = eventBaseAnnotation.listenerSetter();
                    String methodName = eventBaseAnnotation.methodName();
                    Class <?> listenerType= eventBaseAnnotation.listenerType();
                    try {
                        //拿到Onclick注解中的value方法
                        Method aMethod = annotationType.getDeclaredMethod("value");
                        //取出所有的viewId
                        int[] viewIds = (int[]) aMethod.invoke(annotation,null);
                        //通过InvocationHandler设置代理
                        DynamicHandler handler = new DynamicHandler(activity);
                        handler.addMethod(methodName, method);
                        Object listener = Proxy.newProxyInstance(
                                listenerType.getClassLoader(),
                                new Class<?>[] { listenerType }, handler);
                        //遍历所有的View，设置事件
                        for (int viewId : viewIds)
                        {
                            View view = activity.findViewById(viewId);
                            Method setEventListenerMethod = view.getClass()
                                    .getMethod(listenerSetter, listenerType);
                            setEventListenerMethod.invoke(view, listener);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    private static final String METHOD_FIND_VIEW_BY_ID = "findViewById";

    private static void injectViews(Activity activity) {
        Class<?> clazz = activity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){
            ViewInject viewInject = field.getAnnotation(ViewInject.class);
            if (viewInject != null){
                int viewId = viewInject.value();
                if (viewId != -1){
                    try {
                        Method method = clazz.getMethod(METHOD_FIND_VIEW_BY_ID,int.class);
                        Object resView = method.invoke(activity,viewId);
                        field.setAccessible(true);
                        field.set(activity,resView);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 注入布局
     * @param activity
     */
    private static void injectLayout(Activity activity) {
        Class<?> clazz = activity.getClass();
        ContentView contentview = clazz.getAnnotation(ContentView.class);
        int layoutId = contentview.value();
        activity.setContentView(layoutId);
    }
}
