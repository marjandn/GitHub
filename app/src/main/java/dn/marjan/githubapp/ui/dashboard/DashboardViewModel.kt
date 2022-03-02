package dn.marjan.githubapp.ui.dashboard

import dn.marjan.githubapp.base.BaseViewModel
import dn.marjan.githubapp.base.CoroutineDispatcherProvider
import dn.marjan.githubapp.ui.login.repo.LoginRepository
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    val repository: LoginRepository,
    val dispatcher: CoroutineDispatcherProvider
) : BaseViewModel() {
}