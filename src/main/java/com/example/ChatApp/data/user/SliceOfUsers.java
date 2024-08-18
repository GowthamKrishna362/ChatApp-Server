package com.example.ChatApp.data.user;

import com.example.ChatApp.data.SliceList;
import com.example.ChatApp.data.user.profile.UserProfile;
import com.example.ChatApp.models.User;
import lombok.*;
import org.springframework.data.domain.Slice;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class SliceOfUsers extends SliceList {
    private List<UserProfile> users;

    public SliceOfUsers(Slice<User> users) {
        super(users);
        this.users = users.stream().map(UserProfile::new).toList();
    }
}
