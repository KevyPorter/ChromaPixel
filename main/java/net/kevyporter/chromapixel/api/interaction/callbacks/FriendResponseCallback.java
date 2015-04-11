package net.kevyporter.chromapixel.api.interaction.callbacks;

import java.util.ArrayList;

import net.kevyporter.chromapixel.api.interaction.representations.Friend;

public interface FriendResponseCallback {
    public void onFriendResponse(ArrayList<Friend> friends);
}
