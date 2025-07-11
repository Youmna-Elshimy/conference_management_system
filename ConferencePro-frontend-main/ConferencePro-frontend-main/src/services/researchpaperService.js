import axios from "./axios";

// Create research paper
export const createResearchPaper = async (researchPaper) => {
  try {
    const response = await axios.post("/papers", researchPaper);
    console.log(response);
  } catch (error) {
    console.log(error);
    alert("Research paper submission failed. Please try again.");
  }
};

// View all research papers
export const getAllResearchPapers = async () => {
  try {
    const response = await axios.get("/papers/all");
    console.log(response);
  } catch (error) {
    console.log(error);
    alert("Failed to retrieve all research papers. Please try again.");
  }
};

// One research paper by id (just send a json with attribute "id":"enter id here")
export const getResearchPaper = async (paperId) => {
  try {
    const response = await axios.get(`/papers/${paperId}`);
    console.log(response);
    return response;
  } catch (error) {
    console.log(error);
    alert("Failed to retrieve research paper. Please try again.");
  }
};

// All research papers by given author
export const getAllPapersByAuthor = async (user) => {
  try {
    const response = await axios.post("/papers/by", user, {
      headers: { "Content-Type": "application/json" },
    });
    console.log(response);
    return response;
  } catch (error) {
    console.log(error);
    alert("Failed to retrieve submissions. Please try again.");
  }
};

// Update paper details (paper identified by id, all other details overwritten)
export const updateResearchPaper = async (researchPaper) => {
  try {
    const response = await axios.put("/papers", researchPaper);
    console.log(response);
  } catch (error) {
    console.log(error);
    alert("Failed to update research paper. Please try again.");
  }
};

// DELETE
export const deleteResearchPaper = async (researchPaper) => {
  try {
    const response = await axios.delete("/papers", { data: researchPaper });
    console.log(response);
  } catch (error) {
    console.log(error);
    alert("Research paper deletion failed. Please try again.");
  }
};

// Find research papers by reviewer interests
export const getPapersMatchingInterests = async (reviewer) => {
  try {
    const response = await axios.post("/papers/matching", reviewer);
    return response.data;
  } catch (error) {
    console.log(error);
    throw new Error("Failed to retrieve all papers. Please try again.");
  }
};

// Bid on research paper (paper identified by id, reviewer identified by email)
export const bidOnResearchPaper = async (reviewerEmail, paperId) => {
  try {
    const response = await axios.put(`/papers/bid/${paperId}`, {
      email: reviewerEmail,
    });
    return response.data;
  } catch (error) {
    console.log(error);
    throw new Error("Failed to bid on paper. Please try again.");
  }
};

export const updateResearchPaperStatus = async (paperId, newStatus) => {
  try {
    const response = await axios.put(`/papers/status/${paperId}`, newStatus, {
      headers: {
        "Content-Type": "text/plain",
      },
    });
    return response.data;
  } catch (error) {
    console.error(error);
    throw new Error(
      "Failed to update research paper status. Please try again."
    );
  }
};

const researchpaperService = {
  createResearchPaper,
  getAllResearchPapers,
  getResearchPaper,
  getAllPapersByAuthor,
  updateResearchPaper,
  deleteResearchPaper,
  getPapersMatchingInterests,
  bidOnResearchPaper,
  updateResearchPaperStatus,
};

export default researchpaperService;
