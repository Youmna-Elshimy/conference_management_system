import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import secureLocalStorage from "react-secure-storage";
import userAuthService from "../services/userauthService";

const user = secureLocalStorage.getItem("user");

export const signup = createAsyncThunk(
    "userAuth/signup",
    async (userDetails, thunkAPI) => {
        try {
            await userAuthService.signup(userDetails);
        } catch (error) {
            return thunkAPI.rejectWithValue();
        }
    }
);

export const login = createAsyncThunk(
    "userAuth/login",
    async (userDetails, thunkAPI) => {
        try {
            await userAuthService.login(userDetails);
        } catch (error) {
            return thunkAPI.rejectWithValue();
        }
    }
);

export const logout = createAsyncThunk("userAuth/logout", async () => {
    await userAuthService.logout();
});

const initialState = user
  ? { isLoggedIn: true, user }
  : { isLoggedIn: false, user: null };

export const userauthSlice = createSlice ({
    name: "userAuth",
    initialState,
    extraReducers: {
        [signup.fulfilled]: (state, action) => {
            state.isLoggedIn = false;
        },
        [signup.rejected]: (state, action) => {
            state.isLoggedIn = false;
        },
        [login.fulfilled]: (state, action) => {
            state.isLoggedIn = true;
            state.user = secureLocalStorage.getItem("user");
        },
        [login.rejected]: (state, action) => {
            state.isLoggedIn = false;
            state.user = null;
        },
        [logout.fulfilled]: (state, action) => {
            state.isLoggedIn = false;
            state.user = null;
        },
    }
});

const { reducer } = userauthSlice;
export default reducer;