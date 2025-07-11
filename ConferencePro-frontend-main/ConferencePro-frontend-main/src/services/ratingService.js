import axios from "./axios";

// Create a rating on a review
export const createRatingOnReview = async (rating, reviewId) => {
  try {
    const response = await axios.post(`/ratings/${reviewId}`, rating);
    return response.data;
  } catch (error) {
    console.log(error);
    alert("Failed to create rating. Please try again.");
    throw error;
  }
};

// Get all ratings
export const getAllRatings = async () => {
  try {
    const response = await axios.get("/ratings/all");
    return response.data;
  } catch (error) {
    console.log(error);
    alert("Failed to retrieve ratings. Please try again.");
    throw error;
  }
};

// Get a rating
export const getRating = async (rating) => {
  try {
    const response = await axios.post("/ratings/find", rating);
    return response.data;
  } catch (error) {
    console.log(error);
    alert("Failed to retrieve rating. Please try again.");
    throw error;
  }
};

// Update a rating
export const updateRating = async (rating) => {
  try {
    const response = await axios.put("/ratings", rating);
    return response.data;
  } catch (error) {
    console.log(error);
    alert("Failed to update rating. Please try again.");
    throw error;
  }
};

// Delete a rating
export const deleteRating = async (rating) => {
  try {
    const response = await axios.delete("/ratings", { data: rating });
    return response.data;
  } catch (error) {
    console.log(error);
    alert("Failed to delete rating. Please try again.");
    throw error;
  }
};

const ratingService = {
  createRatingOnReview,
  getAllRatings,
  getRating,
  updateRating,
  deleteRating,
};

export default ratingService;
