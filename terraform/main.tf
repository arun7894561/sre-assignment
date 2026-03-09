provider "aws" {
  region = "ap-south-1"
}

resource "aws_ecr_repository" "service_repo" {
  name = var.service_name
}

resource "aws_iam_role" "service_role" {
  name = "${var.service_name}-role"

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [{
      Action = "sts:AssumeRole"
      Effect = "Allow"
      Principal = {
        Service = "ecs-tasks.amazonaws.com"
      }
    }]
  })
}