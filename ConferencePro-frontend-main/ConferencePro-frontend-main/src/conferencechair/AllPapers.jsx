import React from "react";
import ConfLinks from "./ConfLinks";
import PageHeader from "../components/PageHeader";
import { Tab, Tabs, TabList, TabPanel } from "react-tabs";
import "./styles/common.scss";
import "./styles/all-papers.scss";
import "react-tabs/style/react-tabs.css";
import AssignedPapers from "./components/AssignedPapers";
import UnassignedPapers from "./components/UnassignedPapers";

export default function AllPapers() {
  return (
    <div className="page__container">
      <ConfLinks />

      <div className="page-content__container">
        <PageHeader pageTitle="All Papers" />

        <div className="all_papers_container">
          <Tabs>
            <TabList className="papers_tabs_list">
              <Tab className="papers_tab">Assigned</Tab>
              <Tab className="papers_tab">Unassigned</Tab>
            </TabList>

            <TabPanel>
              <AssignedPapers />
            </TabPanel>
            <TabPanel>
              <UnassignedPapers />
            </TabPanel>
          </Tabs>
        </div>
      </div>
    </div>
  );
}
