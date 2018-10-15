package com.packtpub.appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class HomescreenWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        for (int count=0; count<appWidgetIds.length; count++) {
            RemoteViews appWidgetLayout = new
                    RemoteViews(context.getPackageName(),
                    R.layout.widget);
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            appWidgetLayout.setOnClickPendingIntent(R.id.analogClock, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetIds[count], appWidgetLayout);
        }
    }
}
