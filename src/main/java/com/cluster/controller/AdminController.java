package com.cluster.controller;

import com.cluster.data.Clusters;
import com.cluster.service.AdminService;
import com.cluster.service.User;
import com.cluster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Created by Andrew
 */
@RestController
@RequestMapping(path="/admin")
public class AdminController {

    @Autowired private AdminService adminService;
    @Autowired private UserService userService;

    @GetMapping(path = "/getNumActiveUsers")
    public @ResponseBody int getNumActiveUsers(Principal principal) {
        // verify user is admin
        if (userService.isAdmin(Long.parseLong(principal.getName()))) {
            return adminService.getNumActiveUsers();
        }
        return -1; // user was not an admin`
    }

    @GetMapping(path = "/getNumActiveClusters")
    public @ResponseBody int getNumActiveClusters(Principal principal) {
        // verify user is admin
        if (userService.isAdmin(Long.parseLong(principal.getName()))) {
            return adminService.getNumActiveClusters();
        }
        return -1; // user was not an admin
    }

    @GetMapping(path = "/disbandCluster")
    public @ResponseBody boolean getNumActiveUsers(Principal principal, @RequestParam long clusterId) {
        // verify user is admin
        return userService.isAdmin(Long.parseLong(principal.getName())) && adminService.disbandCluster(clusterId);
    }

    @GetMapping(path = "/getUserInformation")
    public @ResponseBody User getUserInformation(Principal principal, @RequestParam long userId) {
        // verify user is admin
        if (userService.isAdmin(Long.parseLong(principal.getName()))) {
            return adminService.getUserInformation(userId);
        }
        return null;
    }

    @GetMapping(path = "/getNumCompletedClusters")
    public @ResponseBody int getNumCompletedOrders(Principal principal) {
        if (userService.isAdmin(Long.parseLong(principal.getName()))) {
            return adminService.getNumCompletedClusters();
        }
        return -1;
    }

    @GetMapping(path = "/getNumNotCompletedClusters")
    public @ResponseBody int getNumNotCompletedOrders(Principal principal) {
        if (userService.isAdmin(Long.parseLong(principal.getName()))) {
            return adminService.getNumNotCompletedClusters();
        }
        return -1;
    }

    @GetMapping(path = "/getAllClusterHistory")
    public @ResponseBody List<Clusters> getAllClusterHistory(Principal principal) {
        if (userService.isAdmin(Long.parseLong(principal.getName()))) {
            return adminService.getAllClusterHistory();
        }

        return null;
    }

    @GetMapping(path = "/ban")
    public @ResponseBody boolean banUser(Principal principal, @RequestParam long userId) {
        return userService.isAdmin(Long.parseLong(principal.getName())) && adminService.banUser(userId);
    }

    @GetMapping(path = "/unban")
    public @ResponseBody boolean unbanUser(Principal principal, @RequestParam long userId) {
        return userService.isAdmin(Long.parseLong(principal.getName())) && adminService.unbanUser(userId);
    }
}

