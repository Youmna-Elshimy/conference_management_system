import axios from "./axios";

/**
 * Creates a review on a given research paper.
 * @param {Object} review - The review object to be created.
 * @param {string} paperId - The ID of the research paper to associate the review with.
 * @returns {Promise<Object>} A Promise that resolves to the created review.
 * @throws {Error} If the request fails or an error occurs.
 */
export const createReviewOnPaper = async (review, paperId) => {
  try {
    const response = await axios.post(`/reviews/${paperId}`, review);
    return response.data;
  } catch (error) {
    console.log(error);
    throw new Error("Failed to create review. Please try again.");
  }
};

/**
 * Retrieves all reviews.
 * @returns {Promise<Array>} A Promise that resolves to an array of reviews.
 * @throws {Error} If the request fails or an error occurs.
 */
export const getAllReviews = async () => {
  try {
    const response = await axios.get("/reviews/all");
    return response.data;
  } catch (error) {
    console.log(error);
    throw new Error("Failed to retrieve reviews. Please try again.");
  }
};

/**
 * Retrieves a review by its ID.
 * @param {string} id - The ID of the review to retrieve.
 * @returns {Promise<Object>} A Promise that resolves to the retrieved review.
 * @throws {Error} If the request fails or an error occurs.
 */
export const getReview = async (id) => {
  try {
    const response = await axios.get(`/reviews/${id}`, { params: { id: id } });
    return response.data;
  } catch (error) {
    console.log(error);
    throw new Error("Failed to retrieve review. Please try again.");
  }
};

/**
 * Retrieves all papers reviewed by a specific reviewer.
 * @param {Object} reviewer - The reviewer object.
 * @returns {Promise<Array>} A Promise that resolves to an array of papers reviewed by the reviewer.
 * @throws {Error} If the request fails or an error occurs.
 */
export const getPapersReviewedBy = async (reviewer) => {
  try {
    const response = await axios.post("/reviews/papersReviewedBy", reviewer);
    return response.data;
  } catch (error) {
    console.log(error);
    throw new Error(
      "Failed to retrieve papers reviewed by the reviewer. Please try again."
    );
  }
};

/**
 * Retrieves all assigned papers (3/3 reviewers).
 * @returns {Promise<Array>} A Promise that resolves to an array of all assigned papers.
 * @throws {Error} If the request fails or an error occurs.
 */
export const getAllAssignedPapers = async () => {
  try {
    const response = await axios.get("/reviews/getAllAssignedPapers");
    return response.data;
  } catch (error) {
    console.log(error);
    throw new Error(
      "Failed to retrieve all assigned papers. Please try again."
    );
  }
};

/**
 * Retrieves all unassigned papers (<3 reviewers).
 * @returns {Promise<Array>} A Promise that resolves to an array of all unassigned papers.
 * @throws {Error} If the request fails or an error occurs.
 */
export const getAllUnassignedPapers = async () => {
  try {
    const response = await axios.get("/reviews/getAllUnassignedPapers");
    return response.data;
  } catch (error) {
    console.log(error);
    throw new Error(
      "Failed to retrieve all unassigned papers. Please try again."
    );
  }
};

/**
 * Updates a review with new data.
 * @param {Object} review - The updated review object.
 * @returns {Promise<Object>} A Promise that resolves to the updated review.
 * @throws {Error} If the request fails or an error occurs.
 */
export const updateReview = async (review) => {
  try {
    const response = await axios.put("/reviews", review);
    return response.data;
  } catch (error) {
    console.log(error);
    throw new Error("Failed to update review. Please try again.");
  }
};

/**
 * Assign paper (by id) to a given reviewer email
 * @param {Object} reviewer - The reviewer receiving assignment
 * @param {Int} paperId - The id of the paper being assigned
 * @returns {Promise<String>} A Promise that resolves to a success message.
 * @throws {Error} If the request fails or an error occurs.
 */
export const assignPaper = async (reviewer, paperId) => {
  try {
    const response = await axios.put(
      `/reviews/assignPaper/${paperId}`,
      reviewer
    );
    return response.data;
  } catch (error) {
    console.log(error);
    throw new Error("Failed to assign paper. Please try again.");
  }
};

/**
 * Deletes a review.
 * @param {Object} review - The review object to delete.
 * @returns {Promise<string>} A Promise that resolves to a success message.
 * @throws {Error} If the request fails or an error occurs.
 */
export const deleteReview = async (review) => {
  try {
    const response = await axios.delete("/reviews", { data: review });
    return response.data;
  } catch (error) {
    console.log(error);
    throw new Error("Failed to delete review. Please try again.");
  }
};

const reviewService = {
  createReviewOnPaper,
  getAllReviews,
  getReview,
  getPapersReviewedBy,
  getAllAssignedPapers,
  getAllUnassignedPapers,
  updateReview,
  assignPaper,
  deleteReview,
};

export default reviewService;
