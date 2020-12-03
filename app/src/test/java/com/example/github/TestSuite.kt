package com.example.github

import com.example.github.domain.datasource.FollowerDataSourceTest
import com.example.github.domain.datasource.UserDataSourceTest
import com.example.github.domain.db.repo.FollowersRepoTest
import com.example.github.domain.db.repo.UserRepo
import com.example.github.domain.db.repo.UserRepoTest
import com.example.github.domain.usecases.FollowersUseCaseTest
import com.example.github.domain.usecases.UserUseCaseTest
import com.example.github.ui.followers.viewmodel.FollowersViewModelTest
import com.example.github.ui.following.viewmodel.FollowingViewModelTest
import com.example.github.ui.home.viewmodel.UsersViewModelTest
import com.example.github.ui.profile.viewmodel.ProfileViewModelTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(UserDataSourceTest::class, FollowerDataSourceTest::class,
                    UserRepoTest::class, FollowersRepoTest::class,
                    UserUseCaseTest::class, FollowersUseCaseTest::class,
                    UsersViewModelTest::class, ProfileViewModelTest::class,
                    FollowersViewModelTest::class, FollowingViewModelTest::class)
class TestSuite