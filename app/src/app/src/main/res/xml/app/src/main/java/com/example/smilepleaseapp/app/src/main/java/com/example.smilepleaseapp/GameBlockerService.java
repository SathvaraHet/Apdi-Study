package com.example.smilepleaseapp;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.view.accessibility.AccessibilityEvent;
import java.util.Arrays;
import java.util.List;

public class GameBlockerService extends AccessibilityService {

    private static final List<String> BLOCKED_GAMES = Arrays.asList(
        "com.roblox.client",
        "com.dts.freefireth",
        "com.mojang.minecraftpe",
        "com.pubg.krmobile"
    );

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            CharSequence packageNameChar = event.getPackageName();
            if (packageNameChar != null) {
                String packageName = packageNameChar.toString();

                if (BLOCKED_GAMES.contains(packageName)) {
                    performGlobalAction(GLOBAL_ACTION_HOME);

                    Intent intent = new Intent(this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        }
    }

    @Override
    public void onInterrupt() {}
}
