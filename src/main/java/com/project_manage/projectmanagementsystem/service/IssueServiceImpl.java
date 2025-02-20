package com.project_manage.projectmanagementsystem.service;

import com.project_manage.projectmanagementsystem.model.Issue;
import com.project_manage.projectmanagementsystem.repository.IssueRepository;
import com.project_manage.projectmanagementsystem.request.IssueRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceImpl implements IssueService{

    @Autowired
    private IssueRepository issueRepository;

    @Override
    public Optional<Issue> getIssueById(Long issueId) throws Exception {
        Optional<Issue> issue = issueRepository.findById(issueId);
        if(issue.isPresent()){
            return issue;
        }
        throw new Exception("No issues found with issueid"+issueId);
    }

    @Override
    public List<Issue> getIssueByProjectId(Long projectId) throws Exception {
        return issueRepository.findByProjectID(projectId);
    }

    @Override
    public Issue createIssue(IssueRequest issue, Long userid) throws Exception {
        return null;
    }

    @Override
    public String deleteIssue(Long issueId, Long userid) throws Exception {
        return "";
    }

    @Override
    public Issue addUserToIssue(Long issueId, Long userId) throws Exception {
        return null;
    }

    @Override
    public Issue updateStatus(Long issueId, String status) throws Exception {
        return null;
    }
}
