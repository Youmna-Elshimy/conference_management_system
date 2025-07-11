import { configureStore } from "@reduxjs/toolkit";
import userAuthReducer from "./slices/userauthSlice";

const reducer = {
    userAuth: userAuthReducer
}

const store = configureStore({
    reducer: reducer,
    devTools: true,
})

export default store;