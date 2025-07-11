import axios from "./axios";

export const createCommentOnReview = async (comment, paperId) => {
  try {
    const response = await axios.post(`/comments/${paperId}`, comment);
    return response.data;
  } catch (error) {
    console.log(error);
    throw new Error("Failed to create comment. Please try again.");
  }
};

export const getAllComments = async () => {
  try {
    const response = await axios.get("/comments/all");
    return response.data;
  } catch (error) {
    console.log(error);
    throw new Error("Failed to retrieve comments. Please try again.");
  }
};

export const getComment = async (comment) => {
  try {
    const response = await axios.post("/comments/find", comment);
    return response.data;
  } catch (error) {
    console.log(error);
    throw new Error("Failed to retrieve comment. Please try again.");
  }
};

export const updateComment = async (comment) => {
  try {
    const response = await axios.put("/comments", comment);
    return response.data;
  } catch (error) {
    console.log(error);
    throw new Error("Failed to update comment. Please try again.");
  }
};

export const deleteComment = async (comment) => {
  try {
    const response = await axios.delete("/comments", { data: comment });
    return response.data;
  } catch (error) {
    console.log(error);
    throw new Error("Failed to delete comment. Please try again.");
  }
};

const commentService = {
  createCommentOnReview,
  getAllComments,
  getComment,
  updateComment,
  deleteComment,
};

export default commentService;
