import axios from "./axios";

// Create a user
export const createUser = async (user) => {
  try {
    const response = await axios.post("/users", user);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

// Get all Reviewers
export const getAllReviewers = async () => {
  try {
    const response = await axios.get("/users/r/all");
    return response.data;
  } catch (error) {
    console.log(error);
    throw error;
  }
};

// Get all Authors
export const getAllAuthors = async () => {
  try {
    const response = await axios.get("/users/a/all");
    return response.data;
  } catch (error) {
    console.log(error);
    throw error;
  }
};

// Get all Conference Chairs
export const getAllConfChairs = async () => {
  try {
    const response = await axios.get("/users/c/all");
    return response.data;
  } catch (error) {
    console.log(error);
    throw error;
  }
};

// Login user
export const getUser = async (user) => {
  try {
    const response = await axios.post("/users/auth", user);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

// Update user details
export const updateUser = async (newUserData) => {
  try {
    const response = await axios.put("/users", newUserData);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

// Delete user account
export const deleteUser = async (user) => {
  try {
    const response = await axios.delete("/users", { data: user });
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};
