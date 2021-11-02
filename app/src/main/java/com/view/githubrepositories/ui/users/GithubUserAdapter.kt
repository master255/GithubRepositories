package com.view.githubrepositories.ui.users

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.view.githubrepositories.R
import com.view.githubrepositories.ui.core.adapter.CollapseOrExpandStateListener
import com.view.githubrepositories.ui.core.adapter.GithubAdapter
import com.view.githubrepositories.ui.core.adapter.GithubOnItemClickListener
import com.view.githubrepositories.ui.core.view.ErrorTextView
import com.view.githubrepositories.ui.core.view.GithubCollapseImageView
import com.view.githubrepositories.ui.core.view.GithubLinearLayout
import com.view.githubrepositories.ui.users.view.UserBioTextView
import com.view.githubrepositories.ui.users.view.UserNameTextView
import com.view.githubrepositories.ui.users.view.UserProfileImageView


/**
 * @author Master255 on 24.08.2021
 * masters@inbox.ru
 */
class GithubUserAdapter(
    private val githubUserItemViewTypeFactory: GithubUserItemViewTypeFactory,
    private val githubUserViewHolderFactory: GithubUserViewHolderFactory,
    ) : RecyclerView.Adapter<GithubUserAdapter.GithubUserViewHolder>(),
    GithubAdapter<UiGithubUserState> {

    private val list = ArrayList<UiGithubUserState>()

    override fun update(list: List<UiGithubUserState>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun update(item: UiGithubUserState,position: Int) {
        this.list[position] = item
        notifyItemChanged(position)
    }

    override fun getItemViewType(position: Int): Int =
        githubUserItemViewTypeFactory.map(list[position])

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GithubUserViewHolder = githubUserViewHolderFactory.map(Pair(viewType, parent))

    override fun onBindViewHolder(holder: GithubUserViewHolder, position: Int)
        =  holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    abstract class GithubUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        open fun bind(state: UiGithubUserState) {}

        class Progress(itemView: View) : GithubUserViewHolder(itemView)

        class Base(
            itemView: View,
            private val onItemClickListener: GithubOnItemClickListener<String>,
            private val collapseOrExpandListener: CollapseOrExpandStateListener<UiGithubUserState>
        ) : GithubUserViewHolder(itemView) {

            private val nameTextView = itemView.findViewById<UserNameTextView>(R.id.name)
            private val bioTextView = itemView.findViewById<UserBioTextView>(R.id.bio)
            private val profileImage = itemView.findViewById<UserProfileImageView>(R.id.image)

            private val collapseImage = itemView.findViewById<GithubCollapseImageView>(R.id.collapse_image)
            private val subItem = itemView.findViewById<GithubLinearLayout>(R.id.sub_item)

            override fun bind(state: UiGithubUserState) {
                state.map(listOf(
                    nameTextView,
                    bioTextView,
                    profileImage
                ))

                state.mapCollapseOrExpandState(
                    listOf(
                        collapseImage,
                        subItem
                    )
                )

                itemView.setOnClickListener {
                    state.notifyAboutItemClick(onItemClickListener)
                }

                collapseImage.setOnClickListener {
                    state.notifyAboutCollapseOrExpand(collapseOrExpandListener,adapterPosition)
                }

            }
        }

        class Empty(itemView: View) : GithubUserViewHolder(itemView) {
            private val emptyDataTextView = itemView.findViewById<ErrorTextView>(R.id.empty_data)

            override fun bind(state: UiGithubUserState) {
                state.map(listOf(emptyDataTextView))
            }
        }

        class Failure(itemView: View) : GithubUserViewHolder(itemView) {
            private val errorTextView = itemView.findViewById<ErrorTextView>(R.id.error)

            override fun bind(state: UiGithubUserState) = state.map(listOf(errorTextView))
        }
    }
}


