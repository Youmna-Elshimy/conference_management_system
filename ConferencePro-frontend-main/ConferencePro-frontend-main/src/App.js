import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./App.scss";

import Landing from "./landing/landing";
import Login from "./landing/login";
import Signup from "./landing/signup";

import RootRoute from "./components/RootRoute";
import ProtectedRoute from "./components/ProtectedRoute";
import UnprotectedRoute from "./components/UnprotectedRoute";

import AuthorDashboard from "./author/AuthorDashboard";
import NewSubmission from "./author/NewSubmission";
import MySubmissions from "./author/MySubmissions";
import ViewSubmissionReviews from "./author/ViewSubmissionReviews";

import ReviewerDashboard from "./reviewer/ReviewerDashboard";
import EditPreferences from "./reviewer/EditPreferences";
import BidForPapers from "./reviewer/BidForPapers";
import AssignedReviews from "./reviewer/AssignedReviews";
import AssignedPaperSummary from "./reviewer/AssignedPaperSummary";
import RateAndReview from "./reviewer/RateAndReview";
import ViewPastReviews from "./reviewer/ViewPastReviews";

import ChairDashboard from "./conferencechair/ChairDashboard";
import Reviewers from "./conferencechair/Reviewers";
import AllPapers from "./conferencechair/AllPapers";
import PaperDetails from "./conferencechair/PaperDetails";
import Inbox from "./conferencechair/Inbox";

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/" element={<RootRoute />} />
          <Route path="/landing" element={<UnprotectedRoute />}>
            <Route index element={<Landing />} />
          </Route>
          <Route path="/login" element={<UnprotectedRoute />}>
            <Route index element={<Login />} />
          </Route>
          <Route path="/signup" element={<UnprotectedRoute />}>
            <Route index element={<Signup />} />
          </Route>

          <Route
            path="/author"
            element={<ProtectedRoute redirectPath="/landing" />}
          >
            <Route index element={<AuthorDashboard />} />
            <Route path="newsubmission" element={<NewSubmission />} />
            <Route path="submissions">
              <Route index element={<MySubmissions />} />
              <Route path=":paperId" element={<ViewSubmissionReviews />} />
            </Route>
          </Route>

          <Route
            path="/reviewer"
            element={<ProtectedRoute redirectPath="/landing" />}
          >
            <Route index element={<ReviewerDashboard />} />
            <Route path="/reviewer/dashboard" element={<ReviewerDashboard />} />
            <Route
              path="/reviewer/edit-preferences"
              element={<EditPreferences />}
            />
            <Route path="/reviewer/bid" element={<BidForPapers />} />
            <Route path="/reviewer/my-reviews" element={<AssignedReviews />} />
            <Route
              path="/reviewer/assigned-paper/:id"
              element={<AssignedPaperSummary />}
            />
            <Route
              path="/reviewer/rating-and-review/:id"
              element={<RateAndReview />}
            />
            <Route
              path="/reviewer/past-reviews/:id"
              element={<ViewPastReviews />}
            />
          </Route>
          <Route
            path="/chair"
            element={<ProtectedRoute redirectPath="/landing" />}
          >
            <Route index element={<ChairDashboard />} />
            <Route path="/chair/dashboard" element={<ChairDashboard />} />
            <Route path="/chair/all-reviewers" element={<Reviewers />} />
            <Route path="/chair/all-papers" element={<AllPapers />} />
            <Route path="/chair/paper/:id" element={<PaperDetails />} />
            <Route path="/chair/inbox" element={<Inbox />} />
          </Route>
        </Routes>
      </Router>
    </div>
  );
}

export default App;
