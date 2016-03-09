package ua.com.itproekt.gup.model.activityfeed;


public enum EventType {
    BLOG_SUBSCRIPTION,

    BLOG_POST_LIKE,
    BLOG_POST_DISLIKE,

    BLOG_POST_COMMENT,
    BLOG_POST_COMMENT_REPLY,
    BLOG_POST_COMMENT_LIKE,

    PROJECT_COMMENT,
    PROJECT_COMMENT_REPLY,

    OFFER_RESERVATION,
    OFFER_RENT,

    MONEY_TRANSFER_TO_USER,
    MONEY_TRANSFER_TO_PROJECT,
    PROJECT_BRING_BACK_MONEY,
    PROJECT_COMPLETE_AMOUNT_REQUESTED,

    //for doerService
    NEW_CLIENT_WANT_CONFIRM,
    USER_ADD_TO_DOER_CLIENT_LIST,

    //for tenderService
    TENDER_END_DAY_NEED_CHOOSE_WINNER,
    YOU_HAVE_BEEN_ADDED_TO_CLOSE_TENDER,
    NEW_PROPOSE_IN_YOUR_TENDER,
    YOU_WON_IN_TENDER
}
